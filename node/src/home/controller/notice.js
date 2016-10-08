/**
 * Created by anzer on 2016/10/7.
 */
'use strict';

import Base from './base.js';
import _ from 'lodash'
import moment from 'moment'
export default class extends Base {
    async getListAction(){
        return this.success(await this.model('official_notice').getList(this.operator.school_id));
    }
}