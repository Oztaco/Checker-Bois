<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
    <!-- <meta http-equiv="refresh" content="10"> -->
    <title>${title} | Web Checkers</title>
    <link rel="stylesheet" type="text/css" href="css\style.css">
    <#if error??>
        <style>
            .modal.login {
                height: 296px;
                width: 280px;
                border-radius: 6px;
                margin: 80px auto;
                box-shadow: 0 12px 50px rgba(0,0,0,0.3);
            }
        </style>
    </#if>
</head>
<body>
<div class="login modal">
    <h1 class="title">Web Checkers</h1>
    <form action="./signin" method="GET">
        <p class="label">Welcome to WebCheckers</p>
        <p class="label">Please pick a username</p>
        <input type="text" name="username" placeholder="Username" />
    <#if error??>
        <p id="error">
            ${error_message}
        </p>
    </#if>
        <input type="submit" value="Play" /><br/>
    </form>

    <p id="numPlayers">Number of Players Signed In: ${numPlayers}</p>
</div>
</body>
</html>