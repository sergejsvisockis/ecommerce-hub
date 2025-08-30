import json
import uuid

from locust import HttpUser, TaskSet, between, task


class UserTasks(TaskSet):

    def on_start(self):
        self.client.headers = {'Content-type': 'application/json'}

    @task
    def make_an_order(self):
        self.client.post("http://localhost:8081/api/v1/orders", json.dumps({
            "orderId": uuid.uuid4(),
            "orderDate": "2025-08-24T09:14:50.931803",
            "product": {
                "productId": uuid.uuid4(),
                "price": {
                    "amount": 150,
                    "currency": "EUR"
                },
                "quantity": 2
            },
            "totalPrice": {
                "amount": 300,
                "currency": "EUR"
            },
            "status": "PLACED",
            "payer": {
                "firstName": "Sergejs",
                "lastName": "Visockis",
                "email": "sergei.visotsky@gmail.com",
                "phone": "+46707396516",
                "address": "Streeet name 20, 11111, City, Country",
                "debitCardDetails": {
                    "cardNumber": "4111111111111111",
                    "cardHolderName": "Sergejs Visockis",
                    "expiryDate": "12/25"
                }
            }
        }, default=str))


class WebsiteUser(HttpUser):
    """
    User class that does requests to the locust web server running on localhost
    """
    wait_time = between(2, 5)
    tasks = [UserTasks]
