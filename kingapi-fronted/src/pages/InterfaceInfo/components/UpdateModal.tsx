import type {ProColumns} from '@ant-design/pro-components';
import {ProTable,} from '@ant-design/pro-components';
import '@umijs/max';
import {Modal} from "antd";
import React, {useEffect, useRef} from 'react';
import {values} from "lodash";
import {ProFromListCommonProps} from "@ant-design/pro-form/es/components/List/ListItem";

export type Props = {
  values:API.InterfaceInfo;
  columns: ProColumns<API.InterfaceInfo>[];
  onCancel: () => void;
  onSubmit: (values: API.InterfaceInfo) => Promise<void>;
  visible: boolean;
};

const UpdateModal: React.FC<Props> = (props) => {
  const {values, columns, visible, onCancel, onSubmit} = props;
  const formRef = useRef<ProFromListCommonProps>();
  useEffect(()=>{
      formRef.current?.setFieldsValue(values);
  },[values])

  return ( <Modal visible={visible} footer={null} onCancel={() => onCancel?.()}>
    <ProTable type="form"
              columns={columns}
              formRef={formRef}
              onSubmit={
                async (value) => {
                  onSubmit?.(value);
                }
              }/>
  </Modal>);
};
export default UpdateModal;
