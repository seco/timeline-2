package com.wedul.wedul_timeline.batch.step.job;

import org.springframework.stereotype.Service;

/**
 * wedul_timeline
 *
 * @author wedul
 * @since 2019-08-05
 **/
@Service("VcncJobService")
public class VcncJobService extends LocketPunchJobService {

    @Override
    protected String getLogoUrl() {
        return "https://image.rocketpunch.com/company/30/vcnc_logo_1559872041.jpg?s=400x400&t=inside";
    }

}
