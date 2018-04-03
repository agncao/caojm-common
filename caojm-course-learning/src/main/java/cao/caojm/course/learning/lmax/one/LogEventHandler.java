package cao.caojm.course.learning.lmax.one;


import cao.caojm.course.learning.lmax.dto.LogEvent;
import com.lmax.disruptor.EventHandler;

/**
 * @author <a href="mailto:caojianmin@jd.com">caojianmin</a>
 * @create 2018/03/30 16:31
 */
public class LogEventHandler implements EventHandler<LogEvent> {
    public void onEvent(LogEvent event, long sequence, boolean endOfBatch)
    {
        System.out.println("Event: " + event);
    }
}
