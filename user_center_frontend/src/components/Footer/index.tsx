import { GithubOutlined } from '@ant-design/icons';
import { DefaultFooter } from '@ant-design/pro-components';
import {PLANET_NET} from "@/constats";
const Footer: React.FC = () => {
  const defaultMessage = 'ICE出品';
  const currentYear = new Date().getFullYear();
  return (
    <DefaultFooter
      copyright={`${currentYear} ${defaultMessage}`}
      links={[
        {
          key: 'WeChat',
          title: '微信',
          href: 'https://pro.ant.design',
          blankTarget: true,
        },
        {
          key: 'github',
          title: <><GithubOutlined />Gitee wbo</>,
          href: PLANET_NET,
          blankTarget: true,
        },
        {
          key: 'planet',
          title: '知识星球',
          href: 'https://ant.design',
          blankTarget: true,
        },
      ]}
    />
  );
};
export default Footer;
