/**
 * Created by anzer on 2016/9/6.
 */
import ColorValue from './color_constant.js'


let Type = {
  TYPE_EXPRESS: 1,
  TYPE_SELL: 2,
  TYPE_HELP: 3,
  TYPE_FOUND: 4,
  TYPE_LOST: 5,
  TYPE_DATING: 6
};
let getMainColor = function (data) {

  if (data.is_end)return ColorValue.COLOR_DEMAND_DISABLED;
  let type = data.ext_type;
  let ext_data = data.ext_data;
  if (type === Type.TYPE_EXPRESS)return  ColorValue.COLOR_DEMAND_BLUE;
  else if (type === Type.TYPE_SELL)return  ColorValue.COLOR_DEMAND_ORANGE;
  else if (type === Type.TYPE_HELP)return  ColorValue.COLOR_DEMAND_PINK;
  else if (type === Type.TYPE_FOUND)return  ColorValue.COLOR_DEMAND_GREEN;
  else if (type === Type.TYPE_LOST)return  ColorValue.COLOR_DEMAND_BLUE;
  else if (type === Type.TYPE_DATING) {
    if (ext_data.want_sex === 1)return  ColorValue.COLOR_DEMAND_BLUE;
    else if (ext_data.want_sex === 2)return  ColorValue.COLOR_DEMAND_PINK;
    else return  ColorValue.COLOR_DEMAND_ORANGE;
  }
};

export default{
  Type:Type,
  getMainColor:getMainColor
}
