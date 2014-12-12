/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.linkedin.camus.etl.kafka.coders;

import com.linkedin.camus.coders.CamusWrapper;
import com.linkedin.camus.coders.MessageDecoder;
import com.linkedin.camus.coders.MessageDecoderException;
import java.io.UnsupportedEncodingException;
import org.apache.commons.lang.StringUtils;

public class StringMessageDecoder extends MessageDecoder<byte[], String> {

    @Override
    public CamusWrapper<String> decode(byte[] payload) {
        String event = StringUtils.EMPTY;
        try {
            event = new String(payload, "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            throw new MessageDecoderException("Unable to deserialize event: " + event, ex);
        }
        CamusWrapper<String> wrapper = new CamusWrapper<String>(event);
        return wrapper;
    }
}