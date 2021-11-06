package com.tt.fly.api;

import cn.hutool.json.JSONUtil;
import com.tt.fly.common.vo.ParcelRequestVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
/**
 * @author molly
 * @date 2021/11/05
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Slf4j
public class ParcelControllerTest {

    @Autowired
    MockMvc mockMvc;


    @Test
   public void countChargeTest() throws Exception {
       log.info("countChargeTest start");
       List<String> weightlist = Arrays.asList("60","20","10","10","8");
       List<String> lengthlist = Arrays.asList("10","10","10","15","25");
       List<String> widthlist = Arrays.asList("10","10","10","10","10");
       List<String> heightlist = Arrays.asList("10","10","10","10","10");
       List<String> voucherlist = Arrays.asList("MYNT","MYNT","MYNT","MYNT","123");

        for(int i=0; i< 5; i++) {
            ParcelRequestVO firstStageRequest = new ParcelRequestVO();
            firstStageRequest.setWeight(new BigDecimal(weightlist.get(i)));
            firstStageRequest.setLength(new BigDecimal(lengthlist.get(i)));
            firstStageRequest.setWidth(new BigDecimal(widthlist.get(i)));
            firstStageRequest.setHeight(new BigDecimal(heightlist.get(i)));
            firstStageRequest.setVoucherCode(voucherlist.get(i));
            String firstStage = JSONUtil.toJsonStr(firstStageRequest);
            ResultActions ra = this.mockMvc
                    .perform(MockMvcRequestBuilders.post(new URI("/parcel/charge/count"))
                            .content(firstStage).contentType(MediaType.APPLICATION_JSON));
            MvcResult result = ra.andReturn();
            log.info("==============={}stageResult={}",i+1, result.getResponse().getContentAsString());
        }
   }

}
