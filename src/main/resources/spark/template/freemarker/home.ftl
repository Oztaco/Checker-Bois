<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
    <!-- <meta http-equiv="refresh" content="10"> -->
    <title>${title} | Web Checkers</title>
    <link rel="stylesheet" type="text/css" href="css\style.css">
</head>
<body>
<div class="login modal">
    <h1 class="title">Web Checkers</h1>
    <form action="./signin" method="GET">
        <p class="label">Welcome to WebCheckers</p>
        <p class="label">Please pick a username</p>
        <input type="text" name="username" placeholder="Username" />
    <#if error??>
        <div>
        ${error_message}
        </div>
    </#if>
        <input type="submit" value="Play" /><br/>
    </form>
</div>
</body>
</html>