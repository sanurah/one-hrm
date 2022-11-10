import {CryptoService} from "../service/crypto.service";
import {environment} from "../../environments/environment";

const tokenUrl = (code: string): string => {
  const clientId = 'client';
  const redirectUri = 'http://127.0.0.1:4200/authorized';
  const grantType = 'authorization_code';
  const codeVerifier = CryptoService.generateCodeVerifier();//'QYPAZ5NU8yvtlQ9erXrUYR-T5AGCjCF47vN-KsaI2A8';//'qPsH306-ZDDaOE8DFzVn05TkN3ZZoVmI_6x4LsVglQI';

  return `${environment.authServer}/oauth2/token?client_id=${clientId}&redirect_uri=${redirectUri}&grant_type=${grantType}&code=${code}&code_verifier=${codeVerifier}`;
};

export {tokenUrl};
