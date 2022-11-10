import {CryptoService} from "../service/crypto.service";
import {environment} from "../../environments/environment";

const redirectUrl = () => {
  const responseType = 'code';
  const clientId = 'client';
  const scope = 'openid';
  const redirectUri = 'http://127.0.0.1:4200/authorized';
  let codeChallenge;
  CryptoService.generateCodeChallenge().then(
    cc => codeChallenge = cc
  );
  const codeChallengeMethod = 'S256';

  const queryParams = `response_type=${responseType}&client_id=${clientId}&scope=${scope}&redirect_uri=${redirectUri}&code_challenge=${codeChallenge}&code_challenge_method=${codeChallengeMethod}`;

  return `${environment.authServer}/oauth2/authorize?${queryParams}`;
};

export {redirectUrl};
