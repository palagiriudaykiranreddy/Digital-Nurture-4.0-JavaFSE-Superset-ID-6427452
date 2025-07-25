import React from "react";
import Cart from "./Cart";

class OnlineShopping extends React.Component {
  constructor(props) {
    super(props);
    this.items = [
      { itemname: "Laptop", price: 799 },
      { itemname: "Phone", price: 499 },
      { itemname: "Headphones", price: 199 },
      { itemname: "Mouse", price: 49 },
      { itemname: "Keyboard", price: 89 },
    ];
  }

  render() {
    return (
      <div>
        <h2>Shopping Cart</h2>
        {this.items.map((item, index) => (
          <Cart key={index} itemname={item.itemname} price={item.price} />
        ))}
      </div>
    );
  }
}

export default OnlineShopping;
