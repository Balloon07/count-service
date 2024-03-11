package com.balloon.core.repository.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author 王思远
 * @date 2024-02-27 16:34
 */
@Data
public class CycleInfoModel {

    /*
        CycleInfoModel 不同周期维度存储值说明：
            1.timeUnit=life时：
                {
                    "cycleCount": 3,
                    "cycleTime": "life"
                }
            2.cycleType=natural & timeInterval=1时：
                {
                    "cycleCount": 3,
                    "cycleTime": "20240227day" ("2024year","202402month","20240226week", etc..)
                }
            3. cycleType=natural & timeInterval!=1时:
                {
                    "cycleRecordList": [
                        {
                            "recordNum": 1,
                            "recordTime": "2024-02-27 10:00:00"
                        },
                        {
                            "recordNum": 2,
                            "recordTime": "2024-02-27 18:00:00"
                        }
                    ]
                }
            4. timeUnit=relative时，同#3
     */

    /**
     * 周期计次统计量
     */
    private Integer cycleCount;

    /**
     * 周期时间
     */
    private String cycleTime;

    /**
     * 周期计次记录
     */
    private List<CycleRecord> cycleRecordList = new ArrayList<>();


    @Data
    public static class CycleRecord {

        /**
         * 计次数量
         */
        private Integer recordNum;

        /**
         * 计次时间
         */
        private Date recordTime;
    }

}
