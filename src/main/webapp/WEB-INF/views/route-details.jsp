<html>
<body>
<div id="container">
    <div id="header"><%@ include file="header.jsp" %></div>
    <div id="middle">
        <table class="table">
            <tr>
                <th>No.</th>
                <th>From</th>
                <th>To</th>
                <th>Distance [km]</th>
            </tr>
            <c:forEach items="${route.roads}" var="road" varStatus="loopCounter">
                <tr>
                    <td>${loopCounter.count}</td>
                    <td>${road.startPlace.shortcut}</td>
                    <td>${road.endPlace.shortcut}</td>
                    <td>${road.roundDistance()}</td>
                </tr>
            </c:forEach>
            <tr>
                <td><a class="btn btn-secondary" href="/delivery/list" role="button">Back</a></td>
                <td></td>
                <th>Total distance</th>
                <td>${route.roundTotalDistance()}</td>
            </tr>
        </table>
    </div>
    <div id="footer"><%@ include file="footer.jsp" %></div>
</div>
</body>
</html>
