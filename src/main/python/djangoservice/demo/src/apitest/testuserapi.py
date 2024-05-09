import requests
import json

class SkipUnserializable(json.JSONEncoder):
    def default(self, obj):
        try:
            return json.JSONEncoder.default(self, obj)
        except TypeError:
            return f"{type(obj)}"

def test_user_api(data, url):
    # Sending the POST request
    try:
        response = requests.post(url, json=data)
        # If the request was successful, print the response
        if response.ok:
            response_dict = response.__dict__
            result = json.dumps(response_dict, cls=SkipUnserializable, indent=4)  # Assuming the response is in JSON format
        else:
            result = json.dumps(f"Failed with status code: {response.status_code}", indent=4)
    except Exception as e:
        result = json.dumps(f"Error: {e}", indent=4)

    file_path = 'testuserapi_test_result.json'
    with open(file_path, 'w') as file:
        # Saving input data for reference
        json.dump(data, file, indent=4)
        file.write("\n")
        # Saving the result
        file.write(result)
    print(f"Saved test result to: {file_path}")


if __name__ == '__main__':
    # URL of the API endpoint
    url = 'http://localhost:8081/user'
    # test original json
    data_original = {
        "userID": "9999999",
        "name": "lanbom测试号",
        "sex": "其他",
        "password": "123456",
        "submit": "Administrator"
    }
    test_user_api(data_original, url)
    print("Test finished")
