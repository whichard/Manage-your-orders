<html>
    <head>
        <meta charset="utf-8">
        <title>卖家商品列表</title>
        <link href="https://cdn.bootcss.com/twitter-bootstrap/3.0.1/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
    <div class="container">
        <div class="row clearfix">
            <div class="col-md-12 column">
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th>订单id</th>
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
                    <#list orderDTOPage.content as orderDTO>
                    <tr>
                        <td>${orderDTO.orderId}</td>
                        <td>${orderDTO.buyerName}</td>
                        <td>${orderDTO.buyerPhone}</td>
                        <td>${orderDTO.buyerAddress}</td>
                        <td>${orderDTO.orderAmount}</td>
                        <td>${orderDTO.orderStatus}</td>
                        <td>${orderDTO.payStatus}</td>
                        <td>${orderDTO.createTime}</td>
                        <td><a href="/seller/order/detail?orderId=${orderDTO.orderId}">详情</a></td>
                        <td>
                            <#if orderDTO.getOrderStatusEnum().message != "已取消">
                                <a href="/seller/order/cancel?orderId=${orderDTO.orderId}">取消</a>
                            </#if>
                            </td>
                    </tr>
                    </#list>
                    </tbody>
                </table>
            </div>
            <#--分页-->
            <div class="col-md-12 column">
                <ul class="pagination pull-right">
                    <#if currentPage lte 1>
                        <li class="disabled"><a href="#">上一页</a></li>
                    <#else>
                        <li><a href="/seller/order/list?page=${currentPage - 1}&size=${size}" class="disabled">上一页</a></li>
                    </#if>

                    <#list 1..orderDTOPage.getTotalPages() as index>
                    <#--当前处在的页面会灰色显示-->
                        <#if currentPage == index>
                            <li class="disabled"><a href="#">${index}</a></li>
                        <#else>
                            <li><a href="/seller/order/list?page=${index}&size=${size}">${index}</a></li>
                        </#if>

                    </#list>
                    <#if currentPage gte orderDTOPage.getTotalPages()>
                        <li class="disabled"><a href="#">下一页</a></li>
                    <#else>
                        <li><a href="/seller/order/list?page=${currentPage + 1}&size=${size}">下一页</a></li>
                    </#if>
                </ul>
            </div>
        </div>
    </div>

<#--弹窗-->
<div class="modal fade" id="myModal" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="myModalLabel">
                    提醒
                </h4>
            </div>
            <div class="modal-body">
                你有新的订单！
            </div>
            <div class="modal-footer">
                <button type="button" onclick="javascript:document.getElementById('notice').pause()" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button onclick="location.reload()" type="button" class="btn btn-primary">查看</button>
            </div>
        </div>

    </div>

</div>

    <#--播放音乐-->
    <audio id="notice" >
        <source src="/mp3/song.mp3" type="audio/mpeg" />
    </audio>

    <script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <script>
        var websocket =null;
        if('WebSocket' in window) {
            websocket = new WebSocket('ws://localhost:8080/webSocket');
        } else
            alert('该浏览器不支持websocket!');
        
        websocket.onopen = function(event) {
            console.log('建立连接');
        }
        
        websocket.onclose = function(event) {
            console.log('连接关闭');
        }
        
        websocket.onmessage = function(event) {
            console.log('收到消息' + event.data);
            //弹窗播放，播放音乐
            $('#myModal').modal('show');
            document.getElementById('notice').play();
        }

        websocket.onerror = function() {
            alert('websocket通信发生错误！');
        }

        window.onbwforeunload = function() {
            websocket.close();
        }
    </script>

    
    </body>
</html>
