import { request as umiReq, history } from 'umi';
import { message } from 'antd';

const loginPath = '/user/login';

async function request() {
  try {
    let result = await umiReq(...arguments);
    console.log(result);

    if (result.errorCode === '100403') {
      // message.error(result.errorMessage);
      history.push(loginPath);
      return;
    }

    return result;
  } catch (error) {
    // 请求 404 504 等错误的时候会进来


    const errorInfo = error.info;
    console.error('Request Error', !errorInfo ? error : errorInfo);
    // 未登录跳转登录页
    // if (errorInfo.errorCode === '100403') {
    //   message.error(errorInfo.errorMessage);
    //   history.push(loginPath);
    //   return;
    // }
    // 其他错误提示
    // message.error(error.errorMessage);
    // 返回的信息
    return errorInfo;
  }
}

export { request };
