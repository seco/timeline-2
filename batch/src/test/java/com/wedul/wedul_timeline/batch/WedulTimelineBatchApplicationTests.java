package com.wedul.wedul_timeline.batch;

import com.wedul.wedul_timeline.batch.timeline.TimeLineJobConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(properties = {"job.name=" + TimeLineJobConfiguration.JOB_NAME})
public class WedulTimelineBatchApplicationTests {

	@Autowired
	private JobLauncherTestUtils jobLauncherTestUtils;

	@Test
	public void 작업_테스트() throws Exception {
		//when
		JobExecution jobExecution = jobLauncherTestUtils.launchJob();

		//then
		assertThat(jobExecution.getStatus(), is(BatchStatus.COMPLETED));
	}

}
