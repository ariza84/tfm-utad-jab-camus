/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.linkedin.camus.etl.kafka.common;

import com.linkedin.camus.coders.CamusWrapper;
import com.linkedin.camus.etl.IEtlKey;
import com.linkedin.camus.etl.kafka.mapred.EtlMultiOutputFormat;
import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.output.FileOutputCommitter;

public class StringKafkaRecordWriterProvider extends StringRecordWriterProvider {
    
    public StringKafkaRecordWriterProvider(TaskAttemptContext  context) {
        super(context);
    }

    @Override
    public RecordWriter<IEtlKey, CamusWrapper> getDataRecordWriter(
            final TaskAttemptContext context,
            final String fileName,
            CamusWrapper data,
            FileOutputCommitter committer) throws IOException, InterruptedException {
        Configuration conf = context.getConfiguration();

        Path file = committer.getWorkPath();
        file = new Path(file, EtlMultiOutputFormat.getUniqueFile(context, fileName, getFilenameExtension()));

        CompressionCodec codec = null;
        SequenceFile.CompressionType compressionType = SequenceFile.CompressionType.NONE;
        
        final SequenceFile.Writer out =
                SequenceFile.createWriter(conf,
                        SequenceFile.Writer.file(file),
                        SequenceFile.Writer.keyClass(Text.class),
                        SequenceFile.Writer.valueClass(Text.class),
                        SequenceFile.Writer.compression(compressionType, codec),
                        SequenceFile.Writer.progressable(context));

        return new RecordWriter<IEtlKey, CamusWrapper>() {

            @Override
            public void write(IEtlKey iEtlKey, CamusWrapper camusWrapper) throws IOException {
                String record = (String) camusWrapper.getRecord() + recordDelimiter;
                out.append(
                        new Text(String.valueOf(iEtlKey.getOffset())),
                        new Text(record.getBytes()));
            }

            @Override
            public void close(TaskAttemptContext taskAttemptContext) throws IOException {
                out.close();
            }
        };
    }
}