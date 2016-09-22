/**
 * Created by anzer on 2016/9/18.
 */
export function token(state){
  return state.user.token;
}
export function login_state(state){
  return state.user.login_state;
}
export function is_login(state){
  return state.user.is_login;
}
