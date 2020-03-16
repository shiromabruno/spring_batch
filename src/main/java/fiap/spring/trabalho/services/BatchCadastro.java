package fiap.spring.trabalho.services;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;

import javax.sql.DataSource;

import fiap.spring.trabalho.domain.Aluno;

@SpringBootApplication
@EnableBatchProcessing
public class BatchCadastro {

	 @Bean
	    public FlatFileItemReader<Aluno> itemReader(@Value("${file.input}") Resource resource) {
	        return new FlatFileItemReaderBuilder<Aluno>()
	                .name("file item reader")
	                .targetType(Aluno.class)
	          //      .delimited().delimiter(delimiter)
	          //      .delimited().delimiter(";").names("nome", "cpf")
	                .resource(resource)
	                .build();
	    }

	    @Bean
	    public ItemProcessor<Aluno, Aluno> itemProcessor() {
	        return Aluno -> {
	            Aluno.setNome(Aluno.getNome().toUpperCase());
	            Aluno.setRm(
	                    Aluno.getRm()
	                           // .replaceAll("\\.", "")
	                          //  .replace("-", "")
	            );
	            return Aluno;
	        };
	    }

	    @Bean
	    public JdbcBatchItemWriter<Aluno> itemWriter(DataSource dataSource) {
	        return new JdbcBatchItemWriterBuilder<Aluno>()
	                .dataSource(dataSource)
	                .sql("insert into TB_Aluno (nome, cpf) values (:nome, :cpf)")
	                .beanMapped()
	                .build();
	    }

	    @Bean
	    public Step step(StepBuilderFactory stepBuilderFactory,
	                     ItemReader<Aluno> itemReader,
	                     ItemProcessor<Aluno, Aluno> itemProcessor,
	                     ItemWriter<Aluno> itemWriter) {
	        return stepBuilderFactory.get("Aluno step")
	                .<Aluno, Aluno>chunk(2)
	                .reader(itemReader)
	                .processor(itemProcessor)
	                .writer(itemWriter)
	                .build();
	    }

	    @Bean
	    public Job job(JobBuilderFactory jobBuilderFactory,
	                   Step step) {
	        return jobBuilderFactory.get("processar Aluno job")
	                .start(step)
	                .build();
	    }

	    public static void main(String[] args) {
	        SpringApplication.run(BatchCadastro.class, args);
	    }
}
