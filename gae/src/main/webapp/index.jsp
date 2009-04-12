<%--
  ~ Copyright 2009 David Linsin
  ~
  ~   Licensed under the Apache License, Version 2.0 (the "License");
  ~   you may not use this file except in compliance with the License.
  ~   You may obtain a copy of the License at
  ~
  ~        http://www.apache.org/licenses/LICENSE-2.0
  ~
  ~    Unless required by applicable law or agreed to in writing, software
  ~    distributed under the License is distributed on an "AS IS" BASIS,
  ~    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  ~    See the License for the specific language governing permissions and
  ~    limitations under the License.
  --%>

<html>
<body>
<h2>Hello <%=request.getAttribute("name")%></h2>

<form action="helloworld" method="post">
    Enter your name: <input type="text" name="uname">
    <input type="submit">
</form>

<a href="/HelloWorld.groovy">Check names with Groovy</a><br/>
<a href="/helloworld">Check names with Servlet</a>
</body>
</html>
