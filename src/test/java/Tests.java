import com.xuanchengwei.download_manager.entity.FileInfo;
import org.junit.Test;
import org.xuanchengwei.jpa.generator.JpaCodeGenerator;

/**
 * @author XUAN-CW
 * @date 2021/8/21 - 0:3s
 */
public class Tests {


    @Test
    public void JpaCodeGeneratorTest() {

        new JpaCodeGenerator(FileInfo.class).generate();
    }


}
