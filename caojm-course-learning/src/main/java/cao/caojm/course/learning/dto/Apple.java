package cao.caojm.course.learning.dto;

/**
 * @author <a href="mailto:caojianmin@jd.com">caojianmin</a>
 * @create 2018/03/27 16:25
 */
public class Apple {
    private int id;

    public Apple(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Apple [id=" + id + "]";
    }
}
