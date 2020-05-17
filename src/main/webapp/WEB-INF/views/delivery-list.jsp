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
                        <td>
                            <!-- Button trigger modal -->
                            <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#exampleModal">
                                Delete
                            </button>
                            <!-- Modal -->
                            <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                <div class="modal-dialog" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="exampleModalLabel">Confirmation</h5>
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                <span aria-hidden="true">&times;</span>
                                            </button>
                                        </div>
                                        <div class="modal-body">
                                            Are you sure want to delete?
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                            <a class="btn btn-primary" href="/delivery/delete/${deliveryPlan.id}" role="button">Delete</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </td>
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
