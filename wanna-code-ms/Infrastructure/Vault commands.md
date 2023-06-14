# Init vault
vault server --dev --dev-root-token-id="00000000-0000-0000-0000-000000000000"

# URL vault
http://127.0.0.1:8200/
## 2 Registrar configuración en vault
vault kv put secret/booking-micros @booking-micros.json
vault kv put secret/booking-micros @product-micros.json

#
