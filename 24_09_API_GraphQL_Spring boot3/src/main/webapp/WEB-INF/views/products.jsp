<%@ page contentType="text/html; charset=UTF-8" %>
<!doctype html>
<html lang="vi">
<head><meta charset="utf-8"><title>Products (JSP + GraphQL)</title></head>
<body>
  <h1>Products (JSP + AJAX GraphQL)</h1>
  <button id="btnLoad">Tải</button>
  <table id="tbl"><thead>
    <tr><th>ID</th><th>Title</th><th>Price</th></tr>
  </thead><tbody></tbody></table>
<script>
async function gql(q){ const r=await fetch('<c:url value="/graphql"/>',{method:'POST',
  headers:{'Content-Type':'application/json'}, body:JSON.stringify({query:q})});
  const j = await r.json(); if (j.errors) { console.error(j.errors); } return j.data; }
document.getElementById('btnLoad').onclick = async ()=>{
  const q=`query{ productsSortedByPriceAsc{ id title price } }`;
  const data = await gql(q);
  document.querySelector('#tbl tbody').innerHTML =
    (data?.productsSortedByPriceAsc||[]).map(p=>`<tr><td>${p.id}</td><td>${p.title}</td><td>${p.price}</td></tr>`).join('');
};
</script>
</body></html>
