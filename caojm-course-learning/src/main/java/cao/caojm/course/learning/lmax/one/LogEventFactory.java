package cao.caojm.course.learning.lmax.one;


import cao.caojm.course.learning.lmax.dto.LogEvent;
import com.lmax.disruptor.EventFactory;

/**
 * @author <a href="mailto:caojianmin@jd.com">caojianmin</a>
 * @create 2018/03/30 16:29
 */
public class LogEventFactory implements EventFactory<LogEvent> {
    public LogEvent newInstance()
    {
        return new LogEvent();
    }
}