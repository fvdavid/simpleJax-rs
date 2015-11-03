<%-- 
    Document   : addProfile
    Created on : Nov 4, 2015, 3:35:07 AM
    Author     : saddamtbg@gmail.com
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>Simple Rest</title>
    </head>

    <body>
        <div style="padding-left:100px;font-family: monospace;">
            <h2> Create Profile </h2>

            <form action="./rest/profile/add" method="post">
                <div style="width: 200px; text-align: left;">
                    <div style="padding:10px;">
                        Username: <input type="text" name="username"><br>
                    </div>
                    <div style="padding:10px;">
                        Phone: <input type="text" name="phone">
                    </div>
                    <div style="padding:10px;">
                        Address: <input type="text" name="address">
                    </div>
                    <div style="padding:10px;">
                        City: <input type="text" name="city">
                    </div>
                    <div style="padding:10px;">
                        Country: <input type="text" name="country">
                    </div>
                </div>

                <!--String username, String phone, String address, String city, String country-->

                <input type="submit" value="Send">
            </form>

        </div>



    </body>
</html>
