# JWT + Spring Security 6 Demo (04_JWT)

API stateless theo slide JWT:
- `POST /auth/register` {username, password}
- `POST /auth/login` {username, password} → trả về `token`
- `GET /users/me` với header `Authorization: Bearer <token>`

Mặc định dùng H2 in-memory để chạy nhanh. Có thể đổi sang MySQL theo slide bằng cách sửa `application.properties`.
- Secret key là Base64 (HS256) và token hết hạn sau 1h.
