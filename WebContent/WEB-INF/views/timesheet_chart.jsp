<%@ page import="java.io.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%!
    // --- String Join Function
    public String join(ArrayList<?> arr, String del)
    {

        StringBuilder output = new StringBuilder();

        for (int i = 0; i < arr.size(); i++)
        {

            if (i > 0) output.append(del);
            
            // --- Quote strings, only, for JS syntax
            if (arr.get(i) instanceof String) output.append("\"");
            output.append(arr.get(i));
            if (arr.get(i) instanceof String) output.append("\"");
        }

        return output.toString();
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>Temperature History</title>
    <script type="text/javascript" src="https://cdn.zingchart.com/zingchart.min.js"></script>
</head>
<body>
    <script>
        <%
            ArrayList<String> months = new ArrayList<String>();
            ArrayList<Integer> users = new ArrayList<Integer>();

          
            
           /* <c:forEach items="${dataPoints}" var="dataPoint">
        	    months.add("${dataPoint.date}");
            	users.add(Integer.parseInt("${dataPoint.hours}"));
        	
        	 </c:forEach>*/	

           
        %>

        var Timestamp = [<%= join(months, ",") %>];
        var Temperature = [<%= join(users, ",") %>];
        
    </script>
    <script>
    window.onload = function()
    {
        zingchart.render
        ({
            id:"myChart",
            width:"100%",
            height:400,
            data:
            {
                "type":"bar",
                "title":
                {
                    "text":"Temperature Data Pulled from MySQL Database"
                },
                "scale-x":
                {
                    "labels": Timestamp
                },
                "plot":
                {
                    "line-width":1
                },
                "series":
                [
                    {
                      "values":Temperature
                    }
                ]
            }
        });
    };
    </script>
    <h1>Historical Temperature for Class Room B110</h1>
    <div id="myChart"></div>
</body>
</html>