def calculate_price(baseprice: float, specialprice: float, extraprice: float, extras: int, discount: float) -> float:
    # Determine addon discount based on extras
    if extras >= 5:
        addon_discount = 10
    elif extras >= 3:
        addon_discount = 15
    else:
        addon_discount = 0
    
    if (discount > addon_discount):
        addon_discount = discount

    result = (
        baseprice / 100.0 * (100 - discount)
        + specialprice
        + extraprice / 100.0 * (100 - addon_discount)
    )

    return result

def test_calculate_price():
    assert calculate_price(100, 50, 20, 0, 0) == 170.0
    assert calculate_price(100, 50, 20, 3, 0) == 167.0
    assert calculate_price(100, 50, 20, 5, 0) == 168.0
    assert calculate_price(100, 50, 20, 0, 5) == 164.0
    assert calculate_price(100, 50, 20, 3, 5) == 162.0
    assert calculate_price(100, 50, 20, 5, 5) == 163.0
    assert calculate_price(100, 50, 20, 0, 15) == 152.0



test_calculate_price()
