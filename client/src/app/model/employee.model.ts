import {RoleConstant} from "../constants/role.constant";

export default class EmployeeModel {
  private _id: number;
  private _email: string;
  private _password: string;
  private _matchPassword: string;
  private _role: RoleConstant;
  private _verified: boolean;

  constructor(id: number, email: string, password: string, matchPassword: string, role: RoleConstant, verified: boolean) {
    this._id = id;
    this._email = email;
    this._password = password;
    this._matchPassword = matchPassword;
    this._role = role;
    this._verified = verified;
  }

  get id(): number {
    return this._id;
  }

  set id(value: number) {
    this._id = value;
  }

  get email(): string {
    return this._email;
  }

  set email(value: string) {
    this._email = value;
  }

  get password(): string {
    return this._password;
  }

  set password(value: string) {
    this._password = value;
  }

  get matchPassword(): string {
    return this._matchPassword;
  }

  set matchPassword(value: string) {
    this._matchPassword = value;
  }

  get role(): RoleConstant {
    return this._role;
  }

  set role(value: RoleConstant) {
    this._role = value;
  }

  get verified(): boolean {
    return this._verified;
  }

  set verified(value: boolean) {
    this._verified = value;
  }
}
