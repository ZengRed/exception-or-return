package zeng.test.exceptionorreturn;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * 为了两个测试方法互不影响，请在一个方法执行完毕后再执行另一个。
 * <p>
 * 测试5次，得到结果如下，时间单位：ms
 * <pre>
 *     ----------------------------
 *         return   |   exception
 *     ----------------------------
 *         35310    |   42053
 *     ----------------------------
 *         36377    |   40516
 *     ----------------------------
 *         34473    |   41742
 *     ----------------------------
 *         36104    |   40337
 *     ----------------------------
 *         33370    |   43544
 *     ----------------------------
 *
 * </pre>
 * <p>
 * return 5次共175634ms，exception共208192ms, 相差32558ms，前者比后者性能好 15.6% 左右。
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ExceptionOrReturnApplicationTests {

    // 请求次数
    private int count = 1000000;

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

    }


    /**
     * return中断测试
     *
     * @throws Exception
     */
    @Test
    public void testReturn() throws Exception {

        long start = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {


            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/users/createOrElseReturn")
                    .param("name", "foo"))
                    .andReturn();
        }
        long stop = System.currentTimeMillis();

        System.out.println("return 中断耗时：" + (stop - start) + " ms");
    }


    /**
     * exception中断测试
     *
     * @throws Exception
     */
    @Test
    public void testException() throws Exception {
        long start = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {


            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/users/createOrElseThrow")
                    .param("name", "foo"))
                    .andReturn();
        }
        long stop = System.currentTimeMillis();

        System.out.println("exception 中断耗时：" + (stop - start) + " ms");
    }
}