import {CryptoService} from "../service/crypto.service";

const redirectUrl = () => {
  const responseType = 'code';
  const clientId = 'client';
  const scope = 'openid';
  const redirectUri = 'http://127.0.0.1:4200/authorized';
  const codeChallenge = CryptoService.generateCodeChallenge().then(
    a => console.log(a)
  );
  const codeChallengeMethod = 'S256';

  const queryParams = `response_type=${responseType}&client_id=${clientId}&scope=${scope}&redirect_uri=${redirectUri}&code_challenge=${codeChallenge}&code_challenge_method=${codeChallengeMethod}`;

  //return `http://localhost:9000/oauth2/authorize?${queryParams}`;
  return 'http://localhost:9000/oauth2/authorize?response_type=code&client_id=client&scope=openid&redirect_uri=http://127.0.0.1:4200/authorized&code_challenge=QYPAZ5NU8yvtlQ9erXrUYR-T5AGCjCF47vN-KsaI2A8&code_challenge_method=S256';
};

export {redirectUrl};
