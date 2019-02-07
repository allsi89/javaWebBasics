<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <c:import url="templates/head.jsp"/>
</head>
<body>
<div class="container">
    <main>
        <div class="jumbotron">
            <form action="/tubes/create" method="POST">

                <div class="row">
                    <div class="col col-md-12 d-flex justify-content-center">
                        <h1>Create tube</h1>
                    </div>
                </div>

                <hr/>
                <div class="row">
                    <div class="col col-md-12">
                        <div class="row  d-flex justify-content-center">
                            <label for="title">Title</label>
                        </div>
                        <div class="row d-flex justify-content-center">
                            <input type="text" name="title" id="title">
                        </div>
                    </div>
                </div>
                <hr/>
                <div class="row">
                    <div class="col col-md-12">
                        <div class="row  d-flex justify-content-center">
                            <label for="description">Description</label>
                        </div>
                        <div class="row d-flex justify-content-center">
                            <textarea name="description" id="description" rows="4" cols="22"></textarea>
                        </div>
                    </div>
                </div>
                <hr/>
                <div class="row mt-4">
                    <div class="col col-md-12">
                        <div class="row  d-flex justify-content-center">
                            <label for="link">YouTube Link</label>
                        </div>
                        <div class="form-group d-flex justify-content-center">
                            <input type="url" name="youTubeLink" id="link">
                        </div>
                    </div>
                </div>
                <hr/>
                <div class="row">
                    <div class="col col-md-12">
                        <div class="row  d-flex justify-content-center">
                            <label for="uploader">Uploader</label>
                        </div>
                        <div class="row d-flex justify-content-center">
                            <input type="text" name="uploader" id="uploader">
                        </div>
                    </div>
                </div>
                <hr/>
                <div class="row">
                    <div class="col col-md-12 d-flex justify-content-center">
                        <button type="submit" class="btn btn-primary">Create Tube</button>
                    </div>
                </div>
            </form>
            <br/>
            <div class="row">
                <div class="col col-md-12 d-flex justify-content-center">
                    <a href="/">Back to home</a>
                </div>
            </div>
        </div>
    </main>
</div>
<footer>
    <c:import url="templates/footer.jsp"/>
</footer>
</body>
</html>
