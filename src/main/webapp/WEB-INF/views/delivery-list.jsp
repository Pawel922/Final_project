<html>
<body>
<div id="container">
    <div id="header"><%@ include file="header.jsp" %></div>
    <div id="middle">
        <div>
            <table class="table table-striped">
                <tr>
                    <th>No.</th>
                    <th>Delivery date</th>
                    <th>Action</th>
                    <th>Action</th>
                    <th>Action</th>
                </tr>
                <c:forEach items="${deliveryPlans}" var="deliveryPlan" varStatus="loopCounter">
                    <tr>
                        <td>${loopCounter.count}</td>
                        <td>${deliveryPlan.deliveryDate}</td>
                        <td><a class="btn btn-warning" href="/delivery/details/${deliveryPlan.id}" role="button">Details</a></td>
                        <td><a class="btn btn-success" href="/route/find/${deliveryPlan.id}" role="button">Show route</a></td>
                        <td><a class="btn btn-danger" href="/delivery/delete/${deliveryPlan.id}" role="button">Delete</a></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <div>
            <a class="btn btn-primary" href="/delivery/add" role="button">Add new</a>
        </div>
    </div>
</div>
</body>
</html>
