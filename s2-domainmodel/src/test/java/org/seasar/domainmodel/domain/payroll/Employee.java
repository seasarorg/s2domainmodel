package org.seasar.domainmodel.domain.payroll;

import org.seasar.dao.annotation.tiger.Bean;
import org.seasar.domainmodel.domain.annotation.OutsideAggregate;

@Bean
@OutsideAggregate
public class Employee {
    private TimeRecordRepository fTimeRecordRepository;

    public Employee() {}

    //--------------------------------------------------------------------------
    // Getter/Setter
    //--------------------------------------------------------------------------

    public TimeRecordRepository getTimeRecordRepository() {
        return fTimeRecordRepository;
    }

    public void setTimeRecordRepository(
            TimeRecordRepository timeRecordRepository) {
        fTimeRecordRepository = timeRecordRepository;
    }
}
