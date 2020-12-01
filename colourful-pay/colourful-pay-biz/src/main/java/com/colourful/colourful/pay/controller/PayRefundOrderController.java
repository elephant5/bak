package com.colourful.colourful.pay.controller;


import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

/**
 * <p>
 * 退款单信息表 前端控制器
 * </p>
 *
 * @author json
 * @since 2020-07-30
 */
@Controller
@RequestMapping(value = "/payRefundOrder",consumes = MediaType.APPLICATION_JSON_VALUE+";charset=utf-8", produces = MediaType.APPLICATION_JSON_VALUE+";charset=utf-8")
public class PayRefundOrderController {

}

