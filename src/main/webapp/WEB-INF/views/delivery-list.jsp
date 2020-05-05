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
                        <td><a href="/delivery/details/${deliveryPlan.id}">Details</a></td>
                        <td><a href="/route/find/${deliveryPlan.id}">Show route</a></td>
                        <td><a href="/delivery/delete/${deliveryPlan.id}">Delete</a></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <div>
            <a class="btn btn-primary" href="/delivery/add" role="button">Add new</a>
        </div>
    </div>
    <div id="footer"><%@ include file="footer.jsp" %></div>
</div>
</body>
</html>
