<%@ page language="java"  contentType="text/html; charset=UTF-8" %>

<html>
<body>
<h2>Hello World!</h2>

springmvc上传文件
<form name="form1" action="/manage/product/fileupload" method="post" enctype="multipart/form-data">
    <input type="file" name="upload_file" />
    <input type="submit" value="springmvc上传文件" />
        <%--上传用户：<input type="text" name="username"><br/>--%>
        <%--上传文件1：<input type="file" name="file1"><br/>--%>
        <%--上传文件2：<input type="file" name="file2"><br/>--%>
        <%--<input type="submit" value="提交">--%>
</form>

</body>
</html>
