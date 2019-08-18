package com.wedul.wedul_timeline.batch.step.job.locketPunch;

import org.springframework.stereotype.Service;

/**
 * 마이 리얼 트립 채용 서비스
 *
 * @author wedul
 * @since 2019-08-05
 **/
@Service("MyRealTripJobService")
public class MyRealTripJobService extends LocketPunchJobService {

    @Override
    public String logoUrl(String subUrl) {
        return "https://image.rocketpunch.com/company/88/myrealtrip_logo_1551852207.png?s=400x400&t=inside";
    }
}
