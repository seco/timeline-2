package com.wedul.wedul_timeline.batch.job;

import org.springframework.batch.item.database.JpaItemWriter;

import java.util.ArrayList;
import java.util.List;

/**
 * wedul_timeline
 *
 * @author wedul
 * @since 2019-06-11
 **/
public class JpaItemListWriter<T> extends JpaItemWriter<List<T>> {

    private JpaItemWriter<T> jpaItemWriter;

    public JpaItemListWriter(JpaItemWriter<T> jpaItemWriter) {
        this.jpaItemWriter = jpaItemWriter;
    }

    @Override
    public void write(List<? extends List<T>> items) {
        List<T> totalList = new ArrayList<>();

        for(List<T> list: items) {
            totalList.addAll(list);
        }

        jpaItemWriter.write(totalList);
    }
}
