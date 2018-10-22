<html>
    <head>
        <meta charset="utf-8">
        <title>卖家商品</title>
        <link href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.0.1/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <script src="bootstrap/bootstrap.min.css"></script>
    <body>
    <div class="container">
        <div class="row clearfix">
            <div class="col-md-12 column">
                <table class="table table-bordered table-hover">
                    <thead>
                    <tr>
                        <th>订单Id</th>
                        <th>姓名</th>
                        <th>手机号</th>
                        <th>地址</th>
                        <th>金额</th>
                        <th>订单状态</th>
                        <th>支付状态</th>
                        <th>创建时间</th>
                        <th colspan="2">操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <#list orderDTOpage.content as orderDTO>
                    <tr>
                        <td>${orderDTO.orderId}</td>
                        <td>${orderDTO.buyerName}</td>
                        <td>${orderDTO.buyerPhone}</td>
                        <td>${orderDTO.buyerAddress}</td>
                        <td>${orderDTO.orderAmount}</td>
                        <td>${orderDTO.getorderStatusEnum().message}</td>
                        <td>${orderDTO.getpayEnum().message}</td>
                        <td>${orderDTO.createTime}</td>
                        <td>
                            <a href="/sell/seller/order/findOne?orderId=${orderDTO.orderId}">详情</a>
                        </td>
                        <td>
                            <#if orderDTO.getorderStatusEnum().message !="已取消">
                                <a href="/sell/seller/order/cancel?orderId=${orderDTO.orderId}">取消</a>
                            </#if>
                        </td>
                    </tr>
                    </#list>
                    </tbody>
                </table>
                <ul class="pagination pull-right">
                    <#if currentPage lte 1>
                         <li class="disabled"><a href="#">上一页</a></li>
                    <#else >
                         <li ><a href="/sell/seller/order/list?page=${currentPage - 1}&pageSize=${size}">上一页</a></li>
                    </#if>
                    <#list 1..orderDTOpage.getTotalPages() as index>
                    <#if currentPage = index>
                        <li class="disabled"><a href="#">${index}</a>
                    <#else>
                        <li><a href="/sell/seller/order/list?page=${index}&pageSize=${size}">${index}</a>
                    </#if>
                    </#list>
                    <#if currentPage gte orderDTOpage.getTotalPages()>
                        <li class="disabled"><a href="#">下一页</a></li>
                    <#else >
                        <li><a href="/sell/seller/order/list?page=${currentPage + 1}&pageSize=${size}">下一页</a></li>
                    </#if>

                </ul>
            </div>
        </div>
    </div>
    </body>
</html>

