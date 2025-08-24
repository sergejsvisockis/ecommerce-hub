from locust import HttpUser, TaskSet, between, task


class UserTasks(TaskSet):

    @task
    def make_an_order(self):
        self.client.post("http://localhost:8080/api/v1/orders", json={
            "orderId": "f6b6e9d5-48ed-40b3-8f0a-bffe7cc9bc33",
            "orderDate": "2025-08-24T09:14:50.931803",
            "product": {
                "productId": "f6b6e9d5-48ed-40b3-8f0a-bffe7cc9bc33",
                "price": {
                    "amount": 100.99,
                    "currency": "EUR"
                },
                "quantity": 2
            },
            "totalPrice": {
                "amount": 39.98,
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
        })


class WebsiteUser(HttpUser):
    """
    User class that does requests to the locust web server running on localhost
    """
    wait_time = between(2, 5)
    tasks = [UserTasks]
