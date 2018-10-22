<html>
<head>
    <meta charset="utf-8">
    <title>订单详情</title>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.0.1/css/bootstrap.min.css" rel="stylesheet">
</head>
<script src="bootstrap/bootstrap.min.css"></script>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="row clearfix">
                <div class="col-md-4 column">
                    <table class="table table-bordered">
                        <thead>
                        <th>订单Id</th>
                        <th>金额</th>
                        </thead>
                        <tbody>
                         <#list orderDetails as details>
                         <tr>
                             <td>${details.orderId}</td>
                             <td>${details.productPrice}</td>
                         </tr>
                         </#list>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th>商品Id</th>
                            <th>商品名称</th>
                            <th>价格</th>
                            <th>数量</th>
                            <th>总额</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list orderDetails as detail>
                        <tr>
                            <td>${detail.productId}</td>
                            <td>${detail.productName}</td>
                            <td>${detail.productPrice}</td>
                            <td>${detail.productQuantity}</td>
                            <td>${detail.productPrice*detail.productQuantity}</td>
                        </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="row clearfix">
                <#if orderDTO.getorderStatusEnum().message == "新订单">
                <div class="col-md-4 column">
                    <button type="button" class="btn btn-primary active btn-lg"><a href="/sell/seller/order/finish?orderId=${orderDTO.orderId}">完结订单</a></button>
                    <button type="button" class="btn btn-danger btn-lg"><a href="/sell/seller/order/cancel?orderId=${orderDTO.orderId}">取消订单</a></button>
                </div>
                </#if>
        </div>
    </div>
</div>
</body>
</html>

