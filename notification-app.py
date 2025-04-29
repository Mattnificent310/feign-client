from flask import Flask, jsonify

app = Flask(__name__)

@app.route('/orders/order/<id>', methods=['POST'])
def send_order(id):
    # Mock response that will always return with status code 200
    response = {
        "orderId": id,
        "status": "PROCESSED",
        "customer": f"Customer-{id}",
        "items": [
            {"productId": "item1", "quantity": 2},
            {"productId": "item2", "quantity": 1}
        ],
        "totalAmount": 150.00
    }
    return jsonify(response)


if __name__ == '__main__':
    app.run(host='0.0.0.0', port=4000, debug=True)
