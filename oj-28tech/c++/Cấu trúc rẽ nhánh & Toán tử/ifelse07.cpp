#include <iostream>

using namespace std;

int main() {
    long long a, b;
    cin >> a >> b;

    // Find the largest number <= a that is divisible by b
    long long largest_divisible = (a / b) * b;

    // Find the smallest number >= a that is divisible by b
    long long smallest_divisible = (a + b - 1) / b * b;

    cout << largest_divisible << " " << smallest_divisible << endl;

    return 0;
}
