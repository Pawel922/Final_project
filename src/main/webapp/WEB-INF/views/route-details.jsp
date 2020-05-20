<html>
<body>
<div id="container">
    <div id="header"><%@ include file="header.jsp" %></div>
    <div id="middle-dt-route">
        <table class="table table-striped">
            <tr>
                <th>No.</th>
                <th>From</th>
                <th>To</th>
                <th>Distance [km]</th>
                <th>Duration</th>
            </tr>
            <c:forEach items="${route.roads}" var="road" varStatus="loopCounter">
                <tr>
                    <td>${loopCounter.count}</td>
                    <td>${road.startPlace.shortcut}</td>
                    <td>${road.endPlace.shortcut}</td>
                    <td>${road.roundDistance()}</td>
                    <td>${road.duration}</td>
                </tr>
            </c:forEach>
            <tr>
                <sec:authorize access="isAuthenticated()">
                    <td><a class="btn btn-secondary" href="/delivery/list" role="button">Back</a></td>
                </sec:authorize>
                <sec:authorize access="!isAuthenticated()">
                    <td><a class="btn btn-secondary" href="/home" role="button">Back</a></td>
                </sec:authorize>
                <td></td>
                <th>Total:</th>
                <td>${route.roundTotalDistance()}</td>
                <td>${route.getTotalDuration()}</td>
            </tr>
        </table>
    </div>
    <div id="map">
    </div>
    <div id="footer"><%@ include file="footer.jsp" %></div>
</div>
<script src="https://maps.googleapis.com/maps/api/js?key=&callback=initMap"></script>
<script src="/resources/js/route-details.js"></script>
</body>
</html>
