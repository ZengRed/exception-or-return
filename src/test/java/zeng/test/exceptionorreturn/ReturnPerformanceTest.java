package zeng.test.exceptionorreturn;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;

@Ignore
public class ReturnPerformanceTest {


    private static long returnTestTime = 0;


    @Test
    public void testReturn() {

        long start = System.currentTimeMillis();

        for (int i = 0; i < ExceptionPerformanceTest.count; i++) {
            CloseableHttpClient httpClient = HttpClientBuilder.create().build();
            HttpPost httpGet = new HttpPost("http://localhost:8080/users/createOrElseReturn?name=foo");

            CloseableHttpResponse response = null;
            try {
                response = httpClient.execute(httpGet);
                HttpEntity responseEntity = response.getEntity();
                if (responseEntity != null) {
                    System.out.println("响应内容为:" + EntityUtils.toString(responseEntity));
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    // 释放资源
                    if (httpClient != null) {
                        httpClient.close();
                    }
                    if (response != null) {
                        response.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }


        long stop = System.currentTimeMillis();
        returnTestTime = stop - start;

        System.out.println("test return 共耗时：" + (stop - start) + " ms");
    }


}
