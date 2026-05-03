interface Address {
    city: string;
    houseNumber: string;
    street: string;
}
interface Contact {
    phoneNumber: string;
    email: string;
}
export interface User{
 userId: number;
    firstName: string;
    lastName: string;
    username: string;
    password: string;
    address: Address;
    contact: Contact;
}