<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Investments - Banco Digital</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container">
            <a class="navbar-brand" href="${pageContext.request.contextPath}/">Banco Digital</a>
            <div class="collapse navbar-collapse">
                <ul class="navbar-nav ms-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/dashboard">Dashboard</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/transfer">Transfer</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/logout">Logout</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>

    <div class="container mt-5">
        <h2>Investment Portfolio</h2>
        
        <c:if test="${not empty error}">
            <div class="alert alert-danger">${error}</div>
        </c:if>
        
        <div class="row mt-4">
            <div class="col-md-4">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">Fixed Income</h5>
                        <c:forEach items="${rendaFixa}" var="rf">
                            <div class="mb-3">
                                <h6>Investment ID: ${rf.idRendaFixa}</h6>
                                <p>Balance: R$ ${rf.balance}</p>
                                <p>CDB: ${rf.cdb}</p>
                                <p>LCI: ${rf.lci}</p>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
            
            <div class="col-md-4">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">Variable Income</h5>
                        <c:forEach items="${rendaVariavel}" var="rv">
                            <div class="mb-3">
                                <h6>Investment ID: ${rv.idRendaVariavel}</h6>
                                <p>Balance: R$ ${rv.balance}</p>
                                <p>Bovespa: ${rv.bovespa}</p>
                                <p>BMSF: ${rv.bmsf}</p>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
            
            <div class="col-md-4">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">Investment Funds</h5>
                        <c:forEach items="${fundos}" var="f">
                            <div class="mb-3">
                                <h6>Fund ID: ${f.idFundos}</h6>
                                <p>Balance: R$ ${f.balance}</p>
                                <p>Conservative Funds: ${f.fundosConservadores}</p>
                                <p>High Risk Funds: ${f.fundosAltoRisco}</p>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>