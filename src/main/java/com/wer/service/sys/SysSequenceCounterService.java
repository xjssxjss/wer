package com.wer.service.sys;

import com.wer.common.BusinessException;
import com.wer.entity.sys.SysSequenceCounter;
import com.wer.service.base.BaseService;
import com.wer.util.DateUtil;
import com.wer.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: 主键生成器服务类
 * @package_name: com.wer.service.sys
 * @data: 2019-12-2 10:32
 * @author: Sean
 * @version: V1.0
 */

@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class SysSequenceCounterService extends BaseService<SysSequenceCounter>{
    /**
     * 重置模式：每天重置计数器
     */
    public static final int RESET_BY_DAY = 1;
    /**
     * 重置模式：以年度周数为单位重置计数器。周的第一天以该国日历为准，中国第一天是周日
     */
    public static final int RESET_BY_WEEK_OF_YEAR = 2;
    /**
     * 重置模式：当月改变，和周改变时都重置计数器。 周的第一天以该国日历为准，中国第一天是周日
     */
    public static final int RESET_BY_WEEK_OF_MONTH = 3;
    /**
     * 重置模式：每月重置计数器
     */
    public static final int RESET_BY_MONTH = 4;
    /**
     * 重置模式：每年重置计数器
     */
    public static final int RESET_BY_YEAR = 5;
    /**
     * 重置模式：不按时间重置计数器
     */
    public static final int RESET_NEVER = 6;

    /**
     * 取得系统生成的编码
     *
     * @param className
     * @return
     */
    public synchronized String generateCode(String className, String prefixCode) {
        Map<String,String> paramMap = new HashMap<>();
        paramMap.put("name",className);
        List<SysSequenceCounter> list = null;
        try {
            list = queryByParams(paramMap);
        } catch (Exception e) {
            //对外抛出exception异常
            throw new BusinessException(e.getMessage());
        }
        if (list.size() != 1) {
            throw new RuntimeException("Sequence with name [" + className + "] config error.");
        }
        SysSequenceCounter sequence = list.get(0);

        Calendar currentCalendar = Calendar.getInstance();
        //currentCalendar.clear(Calendar.HOUR);
        //currentCalendar.clear(Calendar.MINUTE);
        //currentCalendar.clear(Calendar.SECOND);
        //currentCalendar.clear(Calendar.MILLISECOND);

        Calendar lastCalendar = Calendar.getInstance();
        lastCalendar.clear();
        lastCalendar.setTime(sequence.getChangeDate());
        //lastCalendar.clear(Calendar.HOUR);
        //lastCalendar.clear(Calendar.MINUTE);
        //lastCalendar.clear(Calendar.SECOND);
        //lastCalendar.clear(Calendar.MILLISECOND);

        int counter = 0;
        switch (sequence.getResetMode()) {
            case RESET_BY_DAY:
                if (lastCalendar.get(Calendar.YEAR) == currentCalendar.get(Calendar.YEAR)
                        && lastCalendar.get(Calendar.MONTH) == currentCalendar.get(Calendar.MONTH)
                        && lastCalendar.get(Calendar.DAY_OF_MONTH) == currentCalendar.get(Calendar.DAY_OF_MONTH)) {
                    counter = sequence.getCounter() + sequence.getIncrement();
                } else {
                    counter = sequence.getInitValue();
                }
                break;
            case RESET_BY_WEEK_OF_YEAR:
                if (lastCalendar.get(Calendar.YEAR) == currentCalendar.get(Calendar.YEAR)
                        && lastCalendar.get(Calendar.WEEK_OF_YEAR) == currentCalendar.get(Calendar.WEEK_OF_YEAR)) {
                    counter = sequence.getCounter() + sequence.getIncrement();
                } else {
                    counter = sequence.getInitValue();
                }
                break;
            case RESET_BY_WEEK_OF_MONTH:
                if (lastCalendar.get(Calendar.YEAR) == currentCalendar.get(Calendar.YEAR)
                        && lastCalendar.get(Calendar.MONTH) == currentCalendar.get(Calendar.MONTH)
                        && lastCalendar.get(Calendar.WEEK_OF_MONTH) == currentCalendar.get(Calendar.WEEK_OF_MONTH)) {
                    counter = sequence.getCounter() + sequence.getIncrement();
                } else {
                    counter = sequence.getInitValue();
                }
                break;
            case RESET_BY_MONTH:
                if (lastCalendar.get(Calendar.YEAR) == currentCalendar.get(Calendar.YEAR)
                        && lastCalendar.get(Calendar.MONTH) == currentCalendar.get(Calendar.MONTH)) {
                    counter = sequence.getCounter() + sequence.getIncrement();
                } else {
                    counter = sequence.getInitValue();
                }
                break;
            case RESET_BY_YEAR:
                if (lastCalendar.get(Calendar.YEAR) == currentCalendar.get(Calendar.YEAR)) {
                    counter = sequence.getCounter() + sequence.getIncrement();
                } else {
                    counter = sequence.getInitValue();
                }
                break;
            case RESET_NEVER:
                counter = sequence.getCounter() + sequence.getIncrement();
                break;
            default:
                break;
        }

        sequence.setCounter(counter);
        sequence.setChangeDate(currentCalendar.getTime());
        try {
            update(sequence);
        } catch (Exception e){
            e.printStackTrace();
        }
        StringBuilder sb = new StringBuilder();
        sb.append(StringUtil.toString(sequence.getPrefix()));
        if (!StringUtil.isEmpty(prefixCode))
            sb.append(prefixCode);
        sb.append(StringUtil.toString(sequence.getSeperator()));
        if (!StringUtil.isEmpty(sequence.getDateFormat())) {
            sb.append(DateUtil.parseDateToStr(sequence.getChangeDate(), sequence.getDateFormat()));
            sb.append(StringUtil.toString(sequence.getSeperator()));
        }
        sb.append(StringUtil.formatLong(counter, sequence.getSeqLength()));
        sb.append(StringUtil.toString(sequence.getSeperator()));
        sb.append(StringUtil.toString(sequence.getSuffix()));
        return sb.toString();
    }
}
